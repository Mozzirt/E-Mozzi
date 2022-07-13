import { BrowserRouter, Routes, Route } from "react-router-dom"
import '../App.css'

import App from '../App'

import Test from '../component/Test'

export default function Router() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path='/' element={<App />} />
                <Route path='/test' element={<Test />} />
            </Routes>
        </BrowserRouter>
    )
}