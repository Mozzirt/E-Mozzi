import React, { Component } from "react";
import "./Home.scss";
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";

import QuestionBoard from "Pages/Common/QuestionBoard/QuestionBoard";
class Home extends Component {
  constructor(props) {
    super(props)
    this.state = {
      isSearchMode: false,
      search: ''
    }
  }
  slickRef
  handleClickHeart = () => {
    const { isHeartClicked } = this.state
    this.setState({ isHeartClicked: !isHeartClicked })
  }
  handleClickSearch = () => {
    const { isSearchMode } = this.state
    this.setState({ isSearchMode: !isSearchMode })
  }
  handleChangeSearch = (e) => {
    this.setState({ search: e.target.value})
  }
  render() {
    const { isSearchMode, search } = this.state
    const sliderSettings = {
      dots: true,
      infinite: true,
      speed: 500,
      slidesToShow: 1,
      slidesToScroll: 1,
      variableWidth: true
    }
    return (
      <div className="home-root-container">
        <div className="home-top">
          <div className="img-area">
            <img src="/images/home/mozzi.png" alt="" />
          </div>
          <div className="title-area">
            <div className="title">안녕하세요, 모찌님!</div>
            <div className="subtitle">오늘의 인기 질문들을 풀어보세요!</div>
          </div>
        </div>
        <div className="home-body">
          {
            isSearchMode
              ?
              <div className="search-area">
                  <input type="text" placeholder="검색단어" value={search} onChange={this.handleChangeSearch} />
                  <div className="search-btn"><img src='/images/home/search.svg' alt=''/></div>
                  <div className="cancel-btn" onClick={this.handleClickSearch}>취소</div>
              </div>
              :
              <div className="filter-area">
                <div className="filter-list">
                  <div>모두보기</div>
                  <div>그룹1</div>
                  <div>그룹2</div>
                </div>
                <div className="filter-img-area">
                  <img src="/images/home/search.svg" alt="" onClick={this.handleClickSearch} />
                </div>
              </div>
          }
          <div className="slide-area">
            <div className="slide-title">베스트</div>
            <Slider {...sliderSettings} ref={ref => this.slickRef = ref}>
              <div className="slide-list" style={{ width: 300 }}></div>
              <div className="slide-list" style={{ width: 300 }}></div>
              <div className="slide-list" style={{ width: 300 }}></div>
              <div className="slide-list" style={{ width: 300 }}></div>
              <div className="slide-list" style={{ width: 300 }}></div>
            </Slider>
          </div>
          <QuestionBoard
            nickname={"저건모야"}
            hashtag={'#OOTD #나의질문'}
            count={10}
            title={"내가 지금 먹고 싶은 것을 맞추시오"}
            heart={1}
            imgUrl={''}
          />
        </div>
      </div>
    )
  }
}
export default Home