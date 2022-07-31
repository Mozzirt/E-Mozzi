import React, { Component } from "react"
import "./MyPage.scss"

import QuestionBoard from "Pages/Common/QuestionBoard/QuestionBoard"

const QUESTION_COUNT = 3
const FRIEND_COUNT = 56
const MY_INFO = {
    id: 1,
    nickname: "이건모양",
    imgUrl: 'https://i0.wp.com/hanamon.kr/wp-content/uploads/2022/02/%E1%84%8F%E1%85%A9%E1%84%83%E1%85%B5%E1%86%BC%E1%84%8D%E1%85%A1%E1%86%AF.jpeg?fit=253%2C199&ssl=1'
}
// const questionList = [
//     {
//         nickname: "저건모야",
//         hashtag: '#OOTD #나의질문',
//         count: 10,
//         title: "내가 지금 먹고 싶은 것을 맞추시오",
//         heart: 1,
//         imgUrl: 'https://i.pinimg.com/736x/64/fe/a3/64fea3f8417b800616600e5e55c627ce--php.jpg'
//     },
//     {
//         nickname: "이건모야",
//         hashtag: '#OOTD #나의질문',
//         count: 5,
//         title: "내가 지금 먹고 있는 것을 맞추시오",
//         heart: 3,
//         imgUrl: ''
//     }
// ]
const questionList= []

class MyPage extends Component {
    constructor(props) {
        super(props)
        this.state = {
        }
    }

    renderService = () => {
        const service = [
                {
                    name: '이벤트',
                    imgUrl: '/images/mypage/event.svg'
                },
                {
                    name: '공지사항',
                    imgUrl: '/images/mypage/notice.svg'
                },
                {
                    name: '친목질',
                    imgUrl: '/images/mypage/socializing.svg'
                },
                {
                    name: '문의하기',
                    imgUrl: '/images/mypage/ask.svg'
                },
                {
                    name: '개발자들',
                    imgUrl: '/images/mypage/developers.png'
                },
                {
                    name: '설정',
                    imgUrl: '/images/mypage/setting.svg'
                },
        ]

        return service.map(service => {
            return (
                <div className="service-list">
                    <div className="list-info">
                        <img src={service.imgUrl} alt=''/>
                        <div>{service.name}</div>
                    </div>
                    <div className="btn-area">
                        <img src='/images/mypage/arrow.svg' alt='' />
                    </div>
                </div>
            )
        })
    }

    renderQuestion = () => {
        return(
            <>
            {
                questionList && questionList.length > 0
                ?
                questionList.map(item => {
                    return (
                        <QuestionBoard
                            nickname={item.nickname}
                            hashtag={item.hashtag}
                            count={item.count}
                            title={item.title}
                            heart={item.heart}
                            imgUrl={item.imgUrl}
                        />
                    )
                })
                :
                <div className="text">작성된 문제가 없습니다.</div>
            }
            </>
        ) 
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
                {
                    MY_INFO.id === 1
                        ?
                        <div className="service-area">
                            <div className="title">서비스</div>
                            {this.renderService()}
                        </div>
                        :
                        <div className="mypage-question-board">
                            {this.renderQuestion()}
                        </div>
                }
                
            </div>
        )
    }
}
export default MyPage