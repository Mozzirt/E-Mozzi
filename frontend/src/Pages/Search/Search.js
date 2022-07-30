import React, { Component } from "react";
import "./Search.scss";

import QuestionBoard from "Pages/Common/QuestionBoard/QuestionBoard";

const questionList = [
    {
        nickname: "저건모야",
        hashtag: '#OOTD #나의질문',
        count: 10,
        title: "내가 지금 먹고 싶은 것을 맞추시오",
        heart: 1,
        imgUrl: 'https://i.pinimg.com/736x/64/fe/a3/64fea3f8417b800616600e5e55c627ce--php.jpg'
    },
    {
        nickname: "이건모야",
        hashtag: '#OOTD #나의질문',
        count: 5,
        title: "내가 지금 먹고 있는 것을 맞추시오",
        heart: 3,
        imgUrl: ''
    }
]

class Search extends Component {
  constructor(props) {
    super(props);
    this.state = {
      isSearchMode: false,
      search: "",
    };
  }
  handleClickHeart = () => {
    const { isHeartClicked } = this.state;
    this.setState({ isHeartClicked: !isHeartClicked });
  };
  handleClickSearch = () => {
    const { isSearchMode } = this.state;
    this.setState({ isSearchMode: !isSearchMode });
  };
  handleChangeSearch = (e) => {
    this.setState({ search: e.target.value });
  };
  renderQuestion = () => {
    return questionList.map(item => {
      return (
        <QuestionBoard
          nickname={item.nickname}
          hashtag={item.hashtag}
          count={item.count}
          title={item.title}
          heart={item.heart}
          imgUrl={item.imgUrl}
        />
      );
    });
  };
  render() {
    const { isSearchMode, search } = this.state;
    return (
      <div className="search-root-container">
        <div className="search-top">
          <div className="img-area">
            <img src="/images/home/mozzi.png" alt="" />
          </div>
          <div className="title-area">
            <div className="title">XXX 검색결과 입니다</div>
          </div>
        </div>
        <div className="search-body">
          {isSearchMode ? (
            <div className="search-area">
              <input
                type="text"
                placeholder="검색단어"
                value={search}
                onChange={this.handleChangeSearch}
              />
              <div className="search-btn">
                <img src="/images/home/search.svg" alt="" />
              </div>
              <div className="cancel-btn" onClick={this.handleClickSearch}>
                취소
              </div>
            </div>
          ) : (
            <div className="filter-area">
              <div className="filter-list">
                <div>질문</div>
                <div>사용자</div>
              </div>
              <div className="filter-img-area">
                <img
                  src="/images/home/search.svg"
                  alt=""
                  onClick={this.handleClickSearch}
                />
              </div>
            </div>
          )}
          <div className="search-question-board">
              <div className="title">검색결과</div>
              {this.renderQuestion()}
          </div>
        </div>
      </div>
    );
  }
}
export default Search