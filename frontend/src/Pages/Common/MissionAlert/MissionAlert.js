import { Drawer, DrawerOverlay, DrawerContent, DrawerHeader, DrawerBody, HStack, Text, Stack, Input, Box, Button, useRadioGroup, Spacer, Image } from '@chakra-ui/react'

export default function MissionAlert(props) {

    const { onClose, isOpen } = props

    return (
      <Drawer placement="bottom" onClose={onClose} isOpen={isOpen}>
          <DrawerOverlay />
          <DrawerContent borderTopRadius="24px" backdropFilter="blur(20px)" bg="linear-gradient(135deg, rgba(255, 255, 255, 0.21) 0%, #FFFFFF 2.07%, rgba(209, 252, 252, 0.21) 92.89%, #FFFFFF 95.89%, rgba(156, 247, 247, 0.12) 99.98%, rgba(62, 240, 240, 0) 99.99%)">
              <DrawerHeader pt={'10px'}>
              </DrawerHeader>
              <DrawerBody m={5} h="500px">
                <Image src='images/MissionAlert/MissionComplete.svg'></Image>
              </DrawerBody>
              <Box>
                  <Button color="white" h="54px" w="100%" bg="linear-gradient(122.69deg, rgba(126, 225, 246, 0.65) 22.21%, rgba(181, 163, 211, 0.65) 80.36%)">확인</Button>
              </Box>
          </DrawerContent>
      </Drawer>
    )
}
