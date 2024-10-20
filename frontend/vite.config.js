import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    host: true,
    proxy: {
      "/api": {
        target: `http://${process.env.HOSTNAME ? process.env.HOSTNAME : "localhost"}:8080`,
        changeOrigin: true,
        secure: false,
      }
    }
  }
})
