import { Drawer, DrawerOverlay, DrawerContent, DrawerHeader, DrawerBody, HStack, Text, Stack, Input, Box, Button, useRadioGroup, Spacer, Image } from '@chakra-ui/react'
import RadioCard from 'Pages/Common/RadioCard/RadioCard'

import { timerCardCss, passwordCardCss } from './BoardSetting-css'

export default function BoardSetting(props) {

    const { onClose, isOpen } = props

    const options = ['없음', '10분', '15분', '30분', '60분', '직접입력']

    const { getRootProps, getRadioProps } = useRadioGroup({
      name: 'framework',
      defaultValue: '없음',
      onChange: console.log,
    })

    const group = getRootProps()

    return (
        <Drawer placement="bottom" onClose={onClose} isOpen={isOpen}>
            <DrawerOverlay />
            <DrawerContent borderTopRadius="24px" backdropFilter="blur(20px)" bg="linear-gradient(135deg, rgba(255, 255, 255, 0.21) 0%, #FFFFFF 2.07%, rgba(209, 252, 252, 0.21) 92.89%, #FFFFFF 95.89%, rgba(156, 247, 247, 0.12) 99.98%, rgba(62, 240, 240, 0) 99.99%)">
                <DrawerHeader pt={'10px'}>
                    <Box h={'32px'} onClick={onClose}>
                        <Image ml={'auto'} src='images/common/close.svg' />
                    </Box>
                    <Text fontFamily="Mulish" fontSize="24px" textAlign="center" fontWeight={700} lineHeight="150%">
                        질문을 등록하시겠어요?
                    </Text>
                </DrawerHeader>
                <DrawerBody pt="4px" pb="24px">
                    <Stack >
                        <Text fontFamily={'Mullish'} fontWeight="600" fontSize={"14px"} lineHeight="150%" marginLeft="12px" opacity={0.8}>타이머 🕰</Text>
                        <HStack {...group} w={5}>
                            {options.map((value) => {
                                const radio = getRadioProps({ value })
                                console.log(radio)
                                return (
                                <RadioCard key={value} {...radio} cardCss={timerCardCss} isExtends={true}>
                                    {value}
                                </RadioCard>
                                )
                            })}
                        </HStack>
                        <Box pl="12px" fontWeight="bold" display="flex" paddingTop="15px">
                            <span>암호 🔒</span>
                            <Spacer></Spacer>
                            <Box paddingRight={2}>
                                <RadioCard key="사용" cssCard={passwordCardCss} isExtends={true}></RadioCard>
                            </Box>
                        </Box>
                    </Stack>
                </DrawerBody>
                <Box>
                    <Button color="white" h="54px" w="100%" bg="linear-gradient(122.69deg, rgba(126, 225, 246, 0.65) 22.21%, rgba(181, 163, 211, 0.65) 80.36%)">확인</Button>
                </Box>
            </DrawerContent>
        </Drawer>
    )
}
