import { createStandaloneToast } from '@chakra-ui/react'

const { toast } = createStandaloneToast()

const notification = (title,description,status) =>{

    toast( {
    title,
    description,
    status ,
    isClosable :true,
     duration : 4000
     })


}

export const successNotification =(title,description)  => {
    notification(
    title,description,"Success"
    )
}

export const errorNotification =(title,description)  => {
    notification(
    title,description,"Error"
    )
}