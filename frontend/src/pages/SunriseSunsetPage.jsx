import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router';
import styled from 'styled-components';
import { formatLocalDateTime } from '../util/UtilMethods';

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

const SunriseSunsetPage = () => {
  const { id } = useParams();
  const [sunriseSunsetInfos, setSunriseSunsetInfos] = useState([]);

  useEffect(() => {
    const fetchSunriseSunsetInfos = async () => {
      const token = localStorage.getItem("token")

      try {
        const response = await fetch(`/api/sunrise-sunset/${id}/sunrise-sunsets`, {
            headers: {
                "Authorization": `Bearer ${token}`
            }
        });

        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }

        const data = await response.json();
        setSunriseSunsetInfos(data);

      } catch(error) {
        console.error("Error fetching sunrise and sunset times by city id: ", error)
      }

      
    };
    fetchSunriseSunsetInfos();
  }, [id]);

  return (
    <div>
      <h2>Sunrise and Sunset Times</h2>
      <Table>
        <thead>
          <tr>
            <TableHeader>Date</TableHeader>
            <TableHeader>Sunrise</TableHeader>
            <TableHeader>Sunset</TableHeader>
          </tr>
        </thead>
        <tbody>
          {sunriseSunsetInfos.map((sunriseSunsetInfo, index) => (
            <TableRow key={index}>
              <TableCell>{sunriseSunsetInfo.date}</TableCell>
              <TableCell>{formatLocalDateTime(sunriseSunsetInfo.sunrise)}</TableCell>
              <TableCell>{formatLocalDateTime(sunriseSunsetInfo.sunset)}</TableCell>
            </TableRow>
          ))}
        </tbody>
      </Table>
    </div>
  );
}

export default SunriseSunsetPage