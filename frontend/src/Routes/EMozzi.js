import React, { Component } from 'react'
import { Routes, Route } from 'react-router-dom'

import Home from 'Pages/Home/Home'
import Search from 'Pages/Search/Search'
import MyPage from 'Pages/MyPage/MyPage'
import Login from 'Pages/Login/Login'
import Mission from 'Pages/Mission/Mission'
import Test from 'Pages/Test/Test'

class EMozzi extends Component {
    render() {
        return (
            <Routes>
                <Route exact path='/' element={<Home/>} />
                <Route path='/search' element={<Search/>} />
                <Route path='/mypage' element={<MyPage/>} />
                <Route path='/login' element={<Login />} />
                <Route path='/mission' element={<Mission />} />
                <Route path='/test' element={<Test />} />
            </Routes>
        )
    }
}

export default EMozzi
