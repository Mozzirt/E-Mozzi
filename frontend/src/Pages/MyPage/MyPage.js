import React, { Component } from "react"
import "./MyPage.scss"

const QUESTION_COUNT = 3
const FRIEND_COUNT = 56
const MY_INFO = {
    nickname: "이건모양",
    imgUrl: 'https://i0.wp.com/hanamon.kr/wp-content/uploads/2022/02/%E1%84%8F%E1%85%A9%E1%84%83%E1%85%B5%E1%86%BC%E1%84%8D%E1%85%A1%E1%86%AF.jpeg?fit=253%2C199&ssl=1'
}

class MyPage extends Component {
    constructor(props) {
        super(props)
        this.state = {
        }
    }

    render() {
        return (
            <div className="mypage-root-container">
                <div className="background"></div>
                <div className="profile-area">
                    <div className="my-profile">
                        <div className="item question">
                            <div className="count">{QUESTION_COUNT}</div>
                            <div className="text">질문들</div>
                        </div>
                        <div className="profile">
                            <div className="profile-img">
                                {
                                    MY_INFO.imgUrl
                                        ?
                                        <img src={MY_INFO.imgUrl} alt='' />
                                        :
                                        null
                                }
                            </div>
                            <div className="my-info">
                                <div className="spacer"></div>
                                <div className="nickname">이건모양</div>
                                <div className="rank">인기인</div>
                            </div>
                        </div>
                        <div className="item friend">
                            <div className="count">{FRIEND_COUNT}</div>
                            <div className="text">친구들</div>
                        </div>
                    </div>
                    <div className="edit-btn">프로필 수정</div>
                </div>
            </div>
        )
    }
}
export default MyPage