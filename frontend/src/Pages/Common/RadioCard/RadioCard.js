import { Box, useRadio } from '@chakra-ui/react'
import  { card as defaultCardCss }  from './RadioCard-css'

export default function RadioCard(props) {
  const { cardCss, isExtends } = props

  let css = defaultCardCss

  if(cardCss) {
    css = isExtends ? {...defaultCardCss, ...cardCss} : cardCss
  }

  console.log(css)

  const { getInputProps, getCheckboxProps } = useRadio(props)

  console.log(props.children);
  const input = getInputProps()
  const checkbox = getCheckboxProps()

  return (
    <Box as='label' id="222">
      <input {...input} />
      <Box
        {...checkbox}
        {...css}
      >
        {props.children}
      </Box>
    </Box>
  )
}