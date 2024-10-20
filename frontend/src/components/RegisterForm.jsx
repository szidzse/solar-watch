import React, { useState } from 'react'
import { useNavigate } from 'react-router';
import styled from 'styled-components';

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

const RegisterForm = () => {
    const [formData, setFormData] = useState({
        firstName: "",
        lastName: "",
        email: "",
        password: "",
    })

    const navigate = useNavigate()

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
      };
    
      const handleSubmit = async (e) => {
        e.preventDefault();
    
        const response = await fetch("/api/member/register", {
          method: "POST",
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify(formData)
        });
    
        if (response.status === 201) {
          navigate("/login");
        } else {
          alert("Registration failed!");
        }
      };
    
      return (
<FormContainer>
          <h2>Register</h2>
          <form onSubmit={handleSubmit}>
            <Label>
              First Name:
              <Input
                type="text"
                name="firstName"
                placeholder="First Name"
                value={formData.firstName}
                onChange={handleChange}
              />
            </Label>
            <Label>
              Last Name:
              <Input
                type="text"
                name="lastName"
                placeholder="Last Name"
                value={formData.lastName}
                onChange={handleChange}
              />
            </Label>
            <Label>
              Email:
              <Input
                type="email"
                name="email"
                placeholder="Email"
                value={formData.email}
                onChange={handleChange}
              />
            </Label>
            <Label>
              Password:
              <Input
                type="password"
                name="password"
                placeholder="Password"
                value={formData.password}
                onChange={handleChange}
              />
            </Label>
            <Button type="submit">Register</Button>
          </form>
        </FormContainer>
      )
}

export default RegisterForm