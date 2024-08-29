import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router';
import styled from 'styled-components';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 2rem;
  max-width: 1000px;
  margin: 0 auto;
  background-color: #f9f9f9;
  border-radius: 8px;
`;

const Table = styled.table`
  width: 100%;
  border-collapse: collapse;
  margin: 2rem 0;
  font-size: 1rem;
  text-align: left;
`;

const TableHeader = styled.th`
  background-color: #f2f2f2;
  padding: 0.75rem;
  border: 1px solid #ddd;
`;

const TableRow = styled.tr`
  border: 1px solid #ddd;
  &:nth-child(even) {
    background-color: #f9f9f9;
  }
`;

const TableCell = styled.td`
  padding: 0.75rem;
  border: 1px solid #ddd;
`;

const Button = styled.button`
  padding: 0.5rem 1rem;
  margin: 0.25rem;
  background-color: #4CAF50;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;

  &:hover {
    background-color: #45a049;
  }
`;

const BackButton = styled(Button)`
  background-color: #007BFF;

  &:hover {
    background-color: #0056b3;
  }
`;

const CitiesPage = () => {
  const [cities, setCities] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchCities = async () => {
      const token = localStorage.getItem("token")
      
      try {
        const response = await fetch('/api/cities', {
            headers: {
                "Authorization": `Bearer ${token}`
            }
        });


        const data = await response.json();
        console.log(data);

        if (!response.ok) {
            throw new Error('Failed to fetch cities.');
        }

        setCities(data);
      } catch(error) {        
        console.error('Error fetching cities:', error);
      }
    };

    fetchCities();
  }, []);

  const handleUpdate = (id) => {
    navigate(`/cities/${id}/edit`);
  };

  const handleDelete = async (id) => {
    const token = localStorage.getItem('token');

    try {
        await fetch(`/api/cities/${id}`, {
            method: 'DELETE',
            headers: {
              "Authorization": `Bearer ${token}`
            }
        });

        setCities(cities.filter(city => city.id !== id));
    } catch(error) {
        console.error('Error deleting city:', error);
    }
  };

  const handleSeeSunriseSunset = (id) => {
    navigate(`/cities/${id}/sunrise-sunset`);
  };

  return (
    <Container>
      <Button onClick={() => navigate('/')}>Back to Home</Button>
      <h2>Cities</h2>
      <Table>
        <thead>
          <tr>
            <TableHeader>City Name</TableHeader>
            <TableHeader>State</TableHeader>
            <TableHeader>Country</TableHeader>
            <TableHeader>Actions</TableHeader>
          </tr>
        </thead>
        <tbody>
          {cities.map(city => (
            <TableRow key={city.id}>
              <TableCell>{city.name}</TableCell>
              <TableCell>{city.state || '- - -'}</TableCell>
              <TableCell>{city.country}</TableCell>
              <TableCell>
                <Button onClick={() => handleUpdate(city.id)}>Update</Button>
                <Button onClick={() => handleDelete(city.id)}>Delete</Button>
                <Button onClick={() => handleSeeSunriseSunset(city.id)}>See Sunrise-Sunset Infos</Button>
              </TableCell>
            </TableRow>
          ))}
        </tbody>
      </Table>
    </Container>
  );
}

export default CitiesPage