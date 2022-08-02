import { Drawer, DrawerOverlay, DrawerContent, DrawerHeader, DrawerBody, HStack, Text, Stack, Input, Box, Button, useRadioGroup, Spacer, Image } from '@chakra-ui/react'

import { textCss, pointCss } from './MissionAlert-css'

export default function MissionAlert(props) {

    const { onClose, isOpen } = props

    return (
      <Drawer placement="bottom" onClose={onClose} isOpen={isOpen}>
          <DrawerOverlay />
          <DrawerContent borderTopRadius="24px" backdropFilter="blur(20px)" bg="linear-gradient(135deg, rgba(255, 255, 255, 0.21) 0%, #FFFFFF 2.07%, rgba(209, 252, 252, 0.21) 92.89%, #FFFFFF 95.89%, rgba(156, 247, 247, 0.12) 99.98%, rgba(62, 240, 240, 0) 99.99%)">
              <DrawerHeader pt={'10px'}>
              </DrawerHeader>
              <DrawerBody p="0px" mt="16px">
                <Box h={159} ml="20%">
                  <Image src='images/MissionAlert/MissionComplete.svg'></Image>
                </Box>
                <Box {...textCss}>
                  <Text>히든목표 달성</Text>
                  <Text {...pointCss}>+ 500 mp</Text>
                </Box>
              </DrawerBody>
              <Box textAlign={'center'}>
                  <Button color="white" h="55px" w="260px" mb='34px' bg="linear-gradient(220deg, #7EE1F6 -14.98%, #B5A3D3 60.27%)" borderRadius="38px">확인</Button>
              </Box>
          </DrawerContent>
      </Drawer>
    )
}
