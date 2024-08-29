import React, { useState } from 'react'
import { useNavigate } from 'react-router';
import styled from 'styled-components';
import Modal from '../components/Modal';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 4rem;
  max-width: 500px;
  margin: 0 auto;
  background-color: #f4f4f4;
  border-radius: 12px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
`;

const Title = styled.h1`
  font-size: 2rem;
  margin-bottom: 2rem;
  color: #333;
`;

const Button = styled.button`
  padding: 1rem 2rem;
  margin: 1rem;
  width: 100%;
  background-color: #4CAF50;
  color: white;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  font-size: 1.2rem;
  transition: background-color 0.3s ease;

  &:hover {
    background-color: #45a049;
  }
`;

const MainPage = () => {
    const [showModal, setShowModal] = useState(false);
    const navigate = useNavigate();
  
    const handleAccessRestrictedPage = (path) => {
      const token = sessionStorage.getItem('token');
  
      if (!token) {
        setShowModal(true);
      } else {
        navigate(path);
      }
    };
  
    const handleCloseModal = () => {
      setShowModal(false);
    };
  
    return (
      <Container>
        <Title>Welcome to Solar Times App</Title>
        <Button onClick={() => navigate('/registration')}>Register</Button>
        <Button onClick={() => navigate('/login')}>Login</Button>
        <Button onClick={() => handleAccessRestrictedPage('/solar-watch')}>Get New Solar Times</Button>
        <Button onClick={() => handleAccessRestrictedPage('/cities')}>Cities Manager</Button>
  
        {showModal && (
          <Modal
            message="First you need to register!"
            onClose={handleCloseModal}
          />
        )}
      </Container>
    );
}

export default MainPage