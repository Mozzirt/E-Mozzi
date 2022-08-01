import { Box, Flex, Text, Image, useDisclosure } from "@chakra-ui/react"


import Join from "../../page/Join"

export default function Login() {

    const { isOpen, onOpen, onClose } = useDisclosure()

    return (
        <Box h="100vh" align="center">
            <Flex align="center" justify="center" h="430px">
                <Image position="absolute" top="11.5%" src="image/logo/main.svg" alt="main logo"></Image>
            </Flex>

            <Box height="22px" bg="linear-gradient(180deg, rgba(196, 196, 196, 0) 0%, rgba(196, 196, 196, 0.063) 100%)"></Box>

            <Join isOpen={isOpen} onClose={onClose}/>

            <Box mt="44px" mb="25px">
                <Image mb="1" src="image/login/kakao.svg" alt="kakao login" onClick={onOpen}></Image>
                <Image mb="1" src="image/login/naver.svg" alt="naver login"></Image>
                <Image mb="1" src="image/login/guest.svg" alt="guest login"></Image>
            </Box>

            <Box>

            <Box fontSize="13px" color='#A8A8A8' letterSpacing="-0.05em">
                <Text m="0px">
                    로그인시 이거모찌의 서비스 약관,
                </Text>
                <Text m="0px">
                    개인정보 취급 방침에 동의하게 됩니다.
                </Text>
            </Box>

            </Box>
            
        </Box>
    )
}
