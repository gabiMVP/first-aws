import {
 Button,Drawer,DrawerOverlay,DrawerContent,DrawerCloseButton,DrawerHeader,DrawerBody,DrawerFooter,useDisclosure,Input

} from '@chakra-ui/react'
import CreateCustomerForm from './CreateCustomerForm.jsx';


const AddIcon = () =>  "+"  ;

const DrawerForm = ( {fetchCustomers}) => {
const { isOpen, onOpen, onClose } = useDisclosure()
    return(
        <>
        <Button leftIcon = {<AddIcon/>}

            onClick = { onOpen}
        >
          Create customer
        </Button>
              <Drawer isOpen={isOpen} onClose={onClose}  size = {"xl"}>
                <DrawerOverlay />
                <DrawerContent>
                  <DrawerCloseButton />
                  <DrawerHeader>Create your account</DrawerHeader>

                  <DrawerBody>
                    <CreateCustomerForm fetchCustomers = {fetchCustomers} />
                  </DrawerBody>

                  <DrawerFooter>
                    <Button type='submit' form='my-form'>
                      Save
                    </Button>
                  </DrawerFooter>
                </DrawerContent>
              </Drawer>

        </>
        )

    }

export default DrawerForm;

