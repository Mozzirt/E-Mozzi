import React, { Component } from "react"
import "./Mission.scss"

class Goals extends Component {
    constructor(props) {
        super(props)
        this.state = {
        }
    }

    renderMission = () => {
        const missions = [
            {
                isComplete : true,
                mission: '현재까지',
                mp: 1000
            },
            {
                isComplete : false,
                mission: '첫문제 출시시',
                mp: 500
            },
            {
                isComplete : false,
                mission: '첫문제 정답시',
                mp: 500
            }
        ]

        return missions.map(item => {
            const { mission, mp, isComplete } = item
            return (
                <div className={`mission-area ${isComplete && 'active'}`}>
                    <div className="mission-area-left">
                        {
                            isComplete
                                ?
                                <div className="mission-title">달성한 히든목표</div>
                                :
                                <div className="mission-title">미달성 히든목표</div>
                        }
                        <div className="mission-des">{`${mission} ${mp} mp 획득`}</div>
                    </div>
                    <div className="mission-area-right"><img src='/images/mission/mission.svg' alt=''/></div>
                </div>
            )
        })
    }

    render() {
        return (
            <div className="mission-root-container">
                <div className="mission-background"><img src='/images/mission/background.svg' alt='' /></div>
                <div className="mission-container">
                    <div className="mission-header">
                        <div className="count">128</div>
                        <div className="title">MISSIONS</div>
                    </div>
                    <div className="mission-des">
                        <div className="des">공개되지 않은 히든 목표가 <span>128개</span> 남았습니다!</div>
                        <div className="des">히든목표를 찾아 mp 포인트로 상품을 구매해보세요!</div>
                    </div>
                    <div className="mission-body">
                        {this.renderMission()}
                    </div>
                </div>
            </div>
        )
    }
}
export default Goals