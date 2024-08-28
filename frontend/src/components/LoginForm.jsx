import React, { useState } from 'react'
import { useNavigate } from 'react-router';

const FormContainer = styled.div`
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

const LoginForm = () => {
    const [formData, setFormData] = useState({
        email: "",
        password: ""
      });
    
      const navigate = useNavigate()
    
      const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
      };
    
      const handleSubmit = async (e) => {
        e.preventDefault();
    
        const response = await fetch("/api/member/login", {
          method: "POST",
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify(formData)
        });
    
        if (response.ok) {
          const data = await response.json();
          localStorage.setItem("token", data.token);
          navigate("/");
        } else {
          alert("Login failed!");
        }
      };
    
      return (
        <FormContainer>
          <h2>Login</h2>
          <form onSubmit={handleSubmit}>
            <Input
              type="email"
              name="email"
              placeholder="Email"
              value={formData.email}
              onChange={handleChange}
            />
            <Input
              type="password"
              name="password"
              placeholder="Password"
              value={formData.password}
              onChange={handleChange}
            />
            <Button type="submit">Login</Button>
          </form>
        </FormContainer>
      )
}

export default LoginForm