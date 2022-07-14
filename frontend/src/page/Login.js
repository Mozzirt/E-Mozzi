import { Box, Flex } from "@chakra-ui/react"




export default function Login() {
    return (
        <div>
            <Flex align="center" justify="center" h="200px">
            Flex Container
            </Flex>
            {/* 로고 */}

            <Box align="center" mb="20">
                <img src="image/login_kakao.svg" alt="kakao"></img>
                <img src="image/login_naver.svg" alt="naver"></img>
            </Box>

            <Box>

                <p>
                    로그인시 이거모찌의 서비스 약관, 개인정보 취급 방침에 동의하게 됩니다.
                </p>

            </Box>
            
        </div>
    )
}
