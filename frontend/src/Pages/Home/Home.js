import React, { Component } from "react";
import "./Home.scss";

import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
 import "slick-carousel/slick/slick-theme.css";

class Home extends Component {
  constructor(props) {
    super(props);
    this.state = {};
  }
  slickRef

  render() {
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
          <div className="filter-area">
            <div className="filter-list">
              <div>모두보기</div>
              <div>그룹1</div>
              <div>그룹2</div>
            </div>
            <div className="filter-img-area">
              <img src="/images/home/search.png" alt="" />
            </div>
          </div>
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
          <div className="question-area">
            <div className="question-info">
                <div className="info-left">
                    <div className="info-img"><img src='' alt=''/></div>
                    <div className="info-content">
                        <div className="title">저건모야</div>
                        <div className="hashtag">#OOTD #나의질문</div>
                    </div>
                </div>
                <div className="info-right">
                    <div className="count-img"><img src='/images/home/question-count.png' alt=''/></div>
                    <div className="count">10문제</div>
                </div>
            </div>
            <div className="question-body">
                <div>내가 지금 먹고 싶은 것을 맞추시오</div>
            </div>
            <div className="question-heart">
                <img src='/images/home/heart.svg' alt=''/>
                <span>1</span>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default Home;
