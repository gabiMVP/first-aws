import {
 Button,Drawer,DrawerOverlay,DrawerContent,DrawerCloseButton,DrawerHeader,DrawerBody,DrawerFooter,useDisclosure,Input

} from '@chakra-ui/react'
import UpdateCustomerForm from './UpdateCustomerForm.jsx';


const AddIcon = () =>  "+"  ;

const DrawerFormUpdate = (  {customer ,fetchCustomers} ) => {
const { isOpen, onOpen, onClose } = useDisclosure()
    return(
        <>
        <Button

            onClick = { onOpen}
        >
          Update Customer
        </Button>
              <Drawer isOpen={isOpen} onClose={onClose}  size = {"xl"}>
                <DrawerOverlay />
                <DrawerContent>
                  <DrawerCloseButton />
                  <DrawerHeader>Update</DrawerHeader>

                  <DrawerBody>
                    <UpdateCustomerForm customer= {customer} fetchCustomers = {fetchCustomers} />
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

export default DrawerFormUpdate;

