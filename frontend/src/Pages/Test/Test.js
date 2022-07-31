import React from 'react'
import axios from 'axios'
import {js_beautify} from 'js-beautify';

import { Box,
         StackDivider,
         Stack,
         VStack,
         Text,
         Input,
         Textarea,
         Button,
         RadioGroup,
         Radio,
         Center,
        } from "@chakra-ui/react"

export default function Test() {
    const [url, setUrl] = React.useState('')
    const [parameter, setParameter] = React.useState('')
    const [method, setMethod] = React.useState('GET')
    const handleChange = (event) => setUrl(event.target.value)
    const handleInputChange = (event) => setParameter(event.target.value)

    function apiCall() {
        const axiosOptions = {
            method,
            url,
        }

        if(method === 'GET') {
            axiosOptions.params = JSON.stringify(parameter)
        } else {
            axiosOptions.data = JSON.stringify(parameter)
        }

        axios(axiosOptions).then(data => setParameter(JSON.stringify(data.data)))
    }

    return (
        <Box m={5} p={3} borderWidth={1} borderRadius={30}>
            <VStack divider={<StackDivider borderColor='gray.200' />} spacing={4} align='stretch'>
                <Box>
                    <Box>
                        <Text pl={3} pb={2} fontWeight={'bold'} display="inline-block">API TEST</Text>
                        <Button colorScheme='blue' size='xs' float={'right'} onClick={apiCall}>call!!</Button>
                    </Box>
                    <Box>
                        <RadioGroup onChange={setMethod} value={method} mb={3}>
                            <Center>
                                <Stack direction='row' spacing={5}>
                                    <Radio value='GET'>GET</Radio>
                                    <Radio value='POST'>POST</Radio>
                                    <Radio value='PUT'>PUT</Radio>
                                    <Radio value='DELETE'>DELETE</Radio>
                                </Stack>
                            </Center>
                        </RadioGroup>
                    </Box>
                    <Input placeholder='URL' size='md' value={url} onChange={handleChange}/>
                    <Textarea placeholder='parameter' value={js_beautify(parameter)} onChange={handleInputChange}/>
                </Box>
                <Box bg='tomato'>
                alert
                </Box>
                <Box bg='pink.100'>
                tooltip
                </Box>
            </VStack>
        </Box>
    )
}

