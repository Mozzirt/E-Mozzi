import { Box, Flex, Text, Image } from "@chakra-ui/react"




export default function Login() {
    return (
        <Box align="center">
            <Flex align="center" justify="center" h="400px" bg="gray.100">
                <Image src="image/logo_main.svg" alt="main logo"></Image>
            </Flex>

            <Box mt="14" mb="14">
                <Image mb="1" src="image/login_kakao.svg" alt="kakao login"></Image>
                <Image mb="1" src="image/login_naver.svg" alt="naver login"></Image>
                <Image mb="1" src="image/login_guest.svg" alt="guest login"></Image>
            </Box>

            <Box>

            <Box fontSize="sm" style={{color:'#A8A8A8'}} mb="24">
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
