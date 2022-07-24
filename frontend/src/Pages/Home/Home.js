import React, { Component } from "react";
import "./Home.scss";
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
// import { Input, InputGroup, InputLeftElement } from '@chakra-ui/react'
// import { SearchIcon } from '@chakra-ui/icons'
import SeachBar from "./SearchBar/SearchBar";
class Home extends Component {
  constructor(props) {
    super(props)
    this.state = {
      isSearchMode: false,
      isHeartClicked: false,
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
  render() {
    const { isHeartClicked, isSearchMode } = this.state
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
                  <SeachBar/>
                {/* <InputGroup>
                    <InputLeftElement
                    pointerEvents='none'
                    children={<SearchIcon color='gray.300' />}
                    />
                    <Input type='tel' placeholder='Phone number' />
                </InputGroup> */}
                {/* <InputGroup className='input-group' display="flex" flexDirection="row">
                  <InputLeftElement
                    className='input-img'
                    pointerEvents="none"
                    children={<SearchIcon color="blue.300" />}
                  />
                  <Input className='input' variant="outline" placeholder='검색단어' />
                </InputGroup> */}
              </div>
              :
              <div className="filter-area">
                <div className="filter-list">
                  <div>모두보기</div>
                  <div>그룹1</div>
                  <div>그룹2</div>
                </div>
                <div className="filter-img-area">
                  <img src="/images/home/search.png" alt="" onClick={this.handleClickSearch} />
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
          <div className="question-area">
            <div className="question-info">
              <div className="info-left">
                <div className="info-img"><img src='' alt='' /></div>
                <div className="info-content">
                  <div className="title">저건모야</div>
                  <div className="hashtag">#OOTD #나의질문</div>
                </div>
              </div>
              <div className="info-right">
                <div className="count-img"><img src='/images/home/question-count.png' alt='' /></div>
                <div className="count">10문제</div>
              </div>
            </div>
            <div className="question-body">
              <div>내가 지금 먹고 싶은 것을 맞추시오</div>
            </div>
            <div className="question-heart" onClick={this.handleClickHeart} >
              {
                isHeartClicked
                  ?
                  <>
                    <img src='/images/home/fill-heart.svg' alt='' />
                    <span>1</span>
                  </>
                  :
                  <>
                    <img src='/images/home/heart.svg' alt='' />
                    <span>0</span>
                  </>
              }
            </div>
          </div>
        </div>
      </div>
    )
  }
}
export default Home