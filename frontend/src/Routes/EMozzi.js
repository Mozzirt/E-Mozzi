import React, { Component } from 'react'
import { Routes, Route } from 'react-router-dom'

import Home from 'Pages/Home/Home'

class EMozzi extends Component {
    render() {
        return (
            <Routes>
                <Route exact path='/' element={<Home/>} />
                <Route path='login' element={<Login />} />
            </Routes>
        )
    }
}

export default EMozzi
