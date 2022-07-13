import { Link, Outlet } from 'react-router-dom'

function App() {
  return (
    <div className="App">
      <nav>
        <header className="App-header"></header>
        <Link to="test">test page</Link>
      </nav>
      <Outlet />
    </div>
  );
}

export default App