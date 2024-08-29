import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router';
import styled from 'styled-components'

const FormContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 2rem;
  max-width: 500px;
  margin: 0 auto;
  background-color: #f9f9f9;
  border-radius: 8px;
`;

const Label = styled.label`
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  margin-bottom: 1rem;
  width: 100%;
`;

const Input = styled.input`
  padding: 0.75rem;
  width: 100%;
  box-sizing: border-box;
  margin-top: 0.5rem;
  border: 1px solid #ccc;
  border-radius: 4px;
`;

const Button = styled.button`
  padding: 0.75rem;
  width: 100%;
  background-color: #4CAF50;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  margin-top: 1rem;

  &:hover {
    background-color: #45a049;
  }
`;

const ResponseContainer = styled.div`
  margin-top: 1.5rem;
  padding: 1rem;
  border: 1px solid #ccc;
  border-radius: 4px;
  width: 100%;
  background-color: #f9f9f9;
`;

function formatLocalDateTime(localDateTime) {
  const date = new Date(localDateTime);
  if (isNaN(date.getTime())) {
      throw new Error("Invalid date format");
  }

  const hours = String(date.getHours()).padStart(2, '0');
  const minutes = String(date.getMinutes()).padStart(2, '0');
  const seconds = String(date.getSeconds()).padStart(2, '0');

  return `${hours}:${minutes}:${seconds}`;
}


const SolarTimesPage = () => {
  const [cityName, setCityName] = useState("");
  const [date, setDate] = useState("");
  const [response, setResponse] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    const token = localStorage.getItem("token");
    if(!token) {
      navigate("/login")
    }
  }, [navigate])

  const handleFetch = async () => {
    const token = localStorage.getItem('token');

    try {
      const res = await fetch(`/api/sunrise-sunset/any?cityName=${cityName}&date=${date}`, {
        headers: {
          'Authorization': `Bearer ${token}`,
          'Content-Type': 'application/json'
        }
      });
      if (!res.ok) {
        throw new Error("Failed to fetch solar times.");
      }
      const data = await res.json();
      setResponse(data);
    } catch (error) {
      console.error("Error fetching solar times:", error);
      setResponse({ error: "Failed to fetch solar times." });
    }
  };

  return (
    <FormContainer>
      <h2>Get Solar Times</h2>
      <Label>
        City Name:
        <Input
          type="text"
          value={cityName}
          onChange={(e) => setCityName(e.target.value)}
          placeholder="Enter city name"
        />
      </Label>
      <Label>
        Date:
        <Input
          type="date"
          value={date}
          onChange={(e) => setDate(e.target.value)}
        />
      </Label>
      <Button onClick={handleFetch}>Get Solar Times</Button>
      {response && (
        <ResponseContainer>
          {response.error ? (
            <p>{response.error}</p>
          ) : (
            <div>
              <p><strong>Sunrise:</strong> {formatLocalDateTime(response.sunrise)}</p>
              <p><strong>Sunset:</strong> {formatLocalDateTime(response.sunset)}</p>
            </div>
          )}
        </ResponseContainer>
      )}
    </FormContainer>
  )
}

export default SolarTimesPage