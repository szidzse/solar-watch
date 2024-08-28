import React, { useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router';
import styled from 'styled-components';

const Container = styled.div`
  max-width: 500px;
  margin: 2rem auto;
  padding: 2rem;
  border: 1px solid #ccc;
  border-radius: 8px;
  background-color: #f9f9f9;
`;

const Label = styled.label`
  display: block;
  margin-bottom: 0.5rem;
`;

const Input = styled.input`
  width: 100%;
  padding: 0.75rem;
  margin-bottom: 1rem;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
`;

const Button = styled.button`
  padding: 0.75rem;
  width: 100%;
  background-color: #4CAF50;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;

  &:hover {
    background-color: #45a049;
  }
`;

const CityUpdatePAge = () => {
    const { id } = useParams();
  const [city, setCity] = useState({ name: '', state: '', country: '' });
  const navigate = useNavigate();

  useEffect(() => {
    const fetchCity = async () => {
      const response = await fetch(`/api/cities/${id}`);
      const data = await response.json();
      setCity(data);
    };
    fetchCity();
  }, [id]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setCity((prevCity) => ({ ...prevCity, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    await fetch(`/api/cities/${id}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(city),
    });
    navigate('/cities');
  };

  return (
    <Container>
      <h2>Update City</h2>
      <form onSubmit={handleSubmit}>
        <Label>City Name</Label>
        <Input
          type="text"
          name="name"
          value={city.name}
          onChange={handleChange}
        />
        <Label>State</Label>
        <Input
          type="text"
          name="state"
          value={city.state}
          onChange={handleChange}
        />
        <Label>Country</Label>
        <Input
          type="text"
          name="country"
          value={city.country}
          onChange={handleChange}
        />
        <Button type="submit">Update City</Button>
      </form>
    </Container>
  );
}

export default CityUpdatePAge