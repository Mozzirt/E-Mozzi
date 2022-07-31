import React, { Component } from 'react'
import { Routes, Route } from 'react-router-dom'

import Home from 'Pages/Home/Home'
import Search from 'Pages/Search/Search'
import Login from 'Pages/Login/Login'

class EMozzi extends Component {
    render() {
        return (
            <Routes>
                <Route exact path='/' element={<Home/>} />
                <Route path='/search' element={<Search/>} />
                <Route path='login' element={<Login />} />
            </Routes>
        )
    }
}

export default EMozzi
