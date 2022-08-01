import { Drawer, DrawerOverlay, DrawerContent, DrawerHeader, DrawerBody, useDisclosure, Text, Stack, Input, Box, Button } from '@chakra-ui/react'



export default function Join(props) {
    const { isOpen, onClose } = props
    
    function test() {
        window.location.href = '/test'
    }

    return (
        <Drawer placement="bottom" onClose={onClose} isOpen={isOpen}>
            <DrawerOverlay bg='rgba(203, 203, 203, 0.71)'/>
            <DrawerContent  borderTopRadius="24px" 
                            backdropFilter="blur(20px)" 
                            bg="linear-gradient(135deg, rgba(255, 255, 255, 0.21) 0.01%, #FFFFFF 4.91%, rgba(209, 252, 252, 0.21) 96.49%, rgba(156, 247, 247, 0.12) 99.98%, rgba(62, 240, 240, 0) 99.99%, #FFFFFF 100%)"
                            boxShadow="inset -5px -5px 4px rgba(255, 255, 255, 0.04), inset 5px 5px 4px rgba(255, 255, 255, 0.08)"
                            >
                <DrawerHeader padding="32px 40px 8px 40px">
                    <Box py="4px">
                        <Text m="0px">🔥</Text>
                    </Box>
                    <Box py="4px">
                        <Text m={0} fontFamily="Mulish" fontSize="25px" fontWeight='600' lineHeight="150%">
                            이거모찌를 이용하기 위한<br /> 추가 정보를 입력해주세요.
                        </Text>
                    </Box>
                    <Box py="4px">
                        <Text m={0} fontSize="15px" color="#9B9B9B" letterSpacing='-0.05em' fontWeight='normal' lineHeight="22px">
                            아래의 정보는 이거모찌 서비스 이용목적 외에<br /> 사용되지 않음을 알려드립니다.
                        </Text>
                    </Box>
                </DrawerHeader>
                <DrawerBody paddingBottom="36px" paddingX='40px'>
                    <Stack spacing='4px'>
                        <Box>
                            <Text mb={0} lineHeight="19px">
                                <span style={{"marginLeft": "14px", "fontSize": "13px", "fontWeight": "500", "color": "#3B3B3B"}}>닉네임</span>
                                <span style={{"marginLeft": "10px", "fontSize": "11px", 'color': '#9B9B9B', 'letterSpacing': '-0.05em'}} ><span style={{"fontWeight": "bold"}}>랜덤 닉네임</span>을 사용해보세요! - beta</span>
                            </Text>
                            <Box pt="1px">
                                <Input fontSize='12px' w="214px" h="46px" focusBorderColor='lime' placeholder='닉네임 입력하람쓰 ~' _placeholder={{color:'black', letterSpacing: '0.04em', opacity:'0.6'}} borderColor="#FA010180" borderRadius='30px' bg='#C3CAC8' pl="16px"/>
                                <Button w="72px" h="30" variant='outline' marginLeft="9px" border='2px solid rgba(99, 109, 112, 0.3)' borderColor="#636D704D" borderRadius='11.5px' fontSize='11px' position='absolute' mt='9px' color='rgba(13, 13, 13, 0.7)'>중복확인</Button>
                            </Box>
                        </Box>
                        <Box>
                            <Text m="0px" fontSize="13px" paddingLeft="14px" fontWeight="500" lineHeight="19px">
                                이메일
                            </Text>
                            <Input pt="1px" pl="16px" w="100%" fontSize='12px' _placeholder={{color:'black', letterSpacing: '0.04em', opacity:'0.6'}} h="46px"  focusBorderColor='pink.400' placeholder='이메일을 입력해주세요' borderColor="#08DB0480" borderRadius='30px' bg='#C3CAC8' />
                        </Box>
                        <Box>
                            <Text m="0px" fontSize="13px" paddingLeft="14px" fontWeight="500" lineHeight="19px">
                                휴대폰
                            </Text>
                            <Input pt="1px" pl="16px" w="100%" pb={0} fontSize='12px' _placeholder={{color:'black', letterSpacing: '0.04em', opacity:'0.6'}} h="46px" type="phone" focusBorderColor='pink.400' placeholder='휴대폰을 입력해주세요' borderRadius='30px' bg='#C3CAC8'/>
                        </Box>
                    </Stack>
                </DrawerBody>
                <Box>
                    <Button color="white" h="54px" w="100%" borderRadius={0} fontFamily="Mulish" letterSpacing="0.04em" fontSize="16px" fontWeight='bold' bg="linear-gradient(105.92deg, rgba(38, 229, 255, 0.65) 14.6%, #C4A0FF 66.02%, #C4A0FF 68.75%)" onClick={test}>확인</Button>
                </Box>
            </DrawerContent>
        </Drawer>
    )
}
