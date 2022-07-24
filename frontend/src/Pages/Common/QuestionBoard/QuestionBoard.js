import React, { Component } from "react";
import "./QuestionBoard.scss";

class QuestionBoard extends Component {
  constructor(props) {
    super(props);
    this.state = {
      isHeartClicked: false,
    };
  }

  handleClickHeart = () => {
    const { isHeartClicked } = this.state;
    this.setState({ isHeartClicked: !isHeartClicked });
  };

  render() {
    const { isHeartClicked } = this.state;
    const { nickname, hashtag, count, title, heart, imgUrl } = this.props

    return (
      <div className="question-area">
        <div className="question-info">
          <div className="info-left">
            <div className="info-img">
              <img src={imgUrl} alt="" />
            </div>
            <div className="info-content">
              <div className="title">{nickname}</div>
              <div className="hashtag">{hashtag}</div>
            </div>
          </div>
          <div className="info-right">
            <div className="count-img">
              <img src="/images/home/question-count.png" alt="" />
            </div>
            <div className="count">{count}문제</div>
          </div>
        </div>
        <div className="question-body">
          <div>{title}</div>
        </div>
        <div className="question-heart" onClick={this.handleClickHeart}>
          {isHeartClicked ? (
            <>
              <img src="/images/home/fill-heart.svg" alt="" />
              <span>{heart+1}</span>
            </>
          ) : (
            <>
              <img src="/images/home/heart.svg" alt="" />
              <span>{heart}</span>
            </>
          )}
        </div>
      </div>
    );
  }
}
export default QuestionBoard;
