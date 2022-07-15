import { BrowserRouter, Routes, Route } from "react-router-dom"
import 'App.css'

import App from 'App'

import Test from 'page/Test'
import Login from 'page/Login'

export default function Router() {
    return (
        <BrowserRouter>
            <header className="App-header"></header>
            <Routes>
                <Route path='/' element={<App />} />
                <Route path='/test' element={<Test />} />
                <Route path='/login' element={<Login />} />
            </Routes>
        </BrowserRouter>
    )
}