'use client'

import {
  Heading,
  Avatar,
  Box,
  Center,
  Image,
  Flex,
  Text,
  Stack,
  Button,
  useColorModeValue,
  Badge ,
} from '@chakra-ui/react'

import {
  AlertDialog,
  AlertDialogBody,
  AlertDialogFooter,
  AlertDialogHeader,
  AlertDialogContent,
  AlertDialogOverlay,
  AlertDialogCloseButton,
} from '@chakra-ui/react'

import { useDisclosure } from '@chakra-ui/react'
import {deleteCustomer} from '../services/client.js'
import React from 'react';
import ReactDOM from 'react-dom';

import DrawerFormUpdate from './DrawerFormUpdate.jsx'

export default function Card( {id,name,email,age,gender,fetchCustomers}     ) {
    const customer = {id,name,email,age,gender}
    console.log("PLOP DRAWER" +  JSON.parse(JSON.stringify(customer)))

    const userGender = gender ==="MALE"?"men":"women";
   const { isOpen, onOpen, onClose } = useDisclosure()
   const cancelRef = React.useRef()
  return (
    <Center py={6}>
      <Box
        maxW={'270px'}
        w={'full'}
        bg={useColorModeValue('white', 'gray.800')}
        boxShadow={'2xl'}
        rounded={'md'}
        overflow={'hidden'}>
        <Image
          h={'120px'}
          w={'full'}
          src={
            'https://images.unsplash.com/photo-1612865547334-09cb8cb455da?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=634&q=80'
          }
          objectFit="cover"
          alt="#"
        />
        <Flex justify={'center'} mt={-12}>
          <Avatar
            size={'xl'}
            src={

              `https://randomuser.me/api/portraits/thumb/${userGender}/${id}.jpg`
            }
            css={{
              border: '2px solid white',
            }}
          />
        </Flex>

        <Box p={6}>
          <Stack spacing={0} align={'center'} mb={5}>
              <Badge>{id}</Badge>
            <Heading fontSize={'2xl'} fontWeight={500} fontFamily={'body'}>
              {name}
            </Heading>
            <Text color={'gray.500'}>{email}</Text>
             <Text color={'gray.500'}>{age}</Text>


          </Stack>
          <Stack>
                           <Button onClick = {onOpen}  >delete</Button>

                            <DrawerFormUpdate  customer= {customer}  fetchCustomers = {fetchCustomers} />


          </Stack>
          <AlertDialog
                  isOpen={isOpen}
                  leastDestructiveRef={cancelRef}
                  onClose={onClose}
                >
                  <AlertDialogOverlay>
                    <AlertDialogContent>
                      <AlertDialogHeader fontSize='lg' fontWeight='bold'>
                        Delete Customer
                      </AlertDialogHeader>

                      <AlertDialogBody>
                        Are you sure? You can't undo this action afterwards.
                      </AlertDialogBody>

                      <AlertDialogFooter>
                        <Button ref={cancelRef} onClick={onClose}>
                          Cancel
                        </Button>
                        <Button colorScheme='red' onClick={ () => {
                                                           deleteCustomer(id).then( res => {
                                                                  onClose()
                                                                  fetchCustomers()
                                                               })
                                                           }
                            } ml={3}>
                          Delete
                        </Button>
                      </AlertDialogFooter>
                    </AlertDialogContent>
                  </AlertDialogOverlay>
                </AlertDialog>

        </Box>
      </Box>
    </Center>
  )
}