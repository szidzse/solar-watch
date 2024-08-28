import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router';
import styled from 'styled-components';

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
      const response = await fetch(`/api/sunrise-sunset/any?cityName=${id}`);
      const data = await response.json();
      setSunriseSunsetInfos(data);
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
          {sunriseSunsetInfos.map((info, index) => (
            <TableRow key={index}>
              <TableCell>{info.sunrise}</TableCell>
              <TableCell>{info.sunset}</TableCell>
            </TableRow>
          ))}
        </tbody>
      </Table>
    </div>
  );
}

export default SunriseSunsetPage