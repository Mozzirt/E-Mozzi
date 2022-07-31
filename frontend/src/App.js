import './App.css'
import EMozzi from 'Routes/EMozzi'

import { ChakraProvider } from '@chakra-ui/react'

function App() {
  return (
    <ChakraProvider>
      <EMozzi />
    </ChakraProvider>
  )
}

export default App
