import { Link } from 'react-router-dom'

function App() {
  return (
    <div className="App">
      <nav>
        <Link to="test">test page</Link>
        <Link to="login">login page</Link>
      </nav>
    </div>
  );
}

export default App