import React from 'react'
import styled from 'styled-components'

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

const ModalButton = styled.button`
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


const Modal = ({ message, onClose }) => {
    return (
        <ModalBackdrop>
          <ModalContent>
            <p>{message}</p>
            <ModalButton onClick={onClose}>Close</ModalButton>
          </ModalContent>
        </ModalBackdrop>
      );
}

export default Modal