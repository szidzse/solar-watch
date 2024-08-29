import React, { useState } from 'react'
import { useNavigate } from 'react-router';
import styled from 'styled-components';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 2rem;
  max-width: 600px;
  margin: 0 auto;
  background-color: #f9f9f9;
  border-radius: 8px;
`;

const Title = styled.h2`
  margin-bottom: 2rem;
`;

const Button = styled.button`
  padding: 1rem 2rem;
  margin: 0.5rem;
  background-color: #4CAF50;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 1rem;

  &:hover {
    background-color: #45a049;
  }
`;

const ModalBackdrop = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
`;

const ModalContent = styled.div`
  background: white;
  padding: 2rem;
  border-radius: 8px;
  text-align: center;
  width: 300px;
`;

const ModalButton = styled(Button)`
  background-color: #007BFF;

  &:hover {
    background-color: #0056b3;
  }
`;

const MainPage = () => {
  const [showModal, setShowModal] = useState(false);
  const navigate = useNavigate();

  const handleAccessRestrictedPage = () => {
    const token = localStorage.getItem('token');

    if (!token) {
      setShowModal(true);
    } else {
      // Navigate to the page if registered
      // This would depend on which button was clicked
    }
  };

  const handleCloseModal = () => {
    setShowModal(false);
  };

  return (
    <Container>
      <Title>Main Page</Title>
      <Button onClick={() => navigate('/registration')}>Register</Button>
      <Button onClick={() => navigate('/login')}>Login</Button>
      <Button onClick={handleAccessRestrictedPage}>Get New Solar Times</Button>
      <Button onClick={handleAccessRestrictedPage}>Cities Manager</Button>

      {showModal && (
        <ModalBackdrop>
          <ModalContent>
            <p>First you need to register!</p>
            <ModalButton onClick={handleCloseModal}>Close</ModalButton>
          </ModalContent>
        </ModalBackdrop>
      )}
    </Container>
  );
}

export default MainPage