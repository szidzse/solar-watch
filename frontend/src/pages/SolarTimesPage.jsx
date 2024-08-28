import React, { useState } from 'react'
import styled from 'styled-components'

const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 2rem;
`;

const Input = styled.input`
  margin: 0.5rem;
  padding: 0.5rem;
  width: 300px;
`;

const Label = styled.label`
  margin: 0.5rem;
  width: 300px;
`;

const Button = styled.button`
  margin: 0.5rem;
  padding: 0.5rem;
  width: 300px;
  background-color: #4CAF50;
  color: white;
  border: none;
  cursor: pointer;

  &:hover {
    background-color: #45a049;
  }
`;

const ResponseContainer = styled.div`
  margin-top: 1rem;
  padding: 1rem;
  border: 1px solid #ccc;
  width: 100%;
  max-width: 600px;
  background-color: #f9f9f9;
`;

const SolarTimesPage = () => {
    const [cityName, setCityName] = useState("");
  const [date, setDate] = useState("");
  const [response, setResponse] = useState(null);

  const handleFetch = async () => {
    try {
      const res = await fetch(`/api/sunrise-sunset/any?cityName=${cityName}&date=${date}`);
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
    <Container>
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
              <p><strong>Sunrise:</strong> {response.sunrise}</p>
              <p><strong>Sunset:</strong> {response.sunset}</p>
            </div>
          )}
        </ResponseContainer>
      )}
    </Container>
  )
}

export default SolarTimesPage