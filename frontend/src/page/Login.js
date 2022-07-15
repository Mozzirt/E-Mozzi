import { Box, color, Flex, Text } from "@chakra-ui/react"




export default function Login() {
    return (
        <Box align="center">
            <Flex align="center" justify="center" h="200px" bg="gray.100">
                <Text>
                    메인 로고 영역
                </Text>
            </Flex>

            <Box mt="20" mb="20">
                <img src="image/login_kakao.svg" alt="kakao"></img>
                <img src="image/login_naver.svg" alt="naver"></img>
            </Box>

            <Box>

            <Box fontSize="sm" style={{color:'#A8A8A8'}}>
                <Text>
                    로그인시 이거모찌의 서비스 약관,
                </Text>
                <Text>
                    개인정보 취급 방침에 동의하게 됩니다.
                </Text>
            </Box>

            </Box>
            
        </Box>
    )
}
