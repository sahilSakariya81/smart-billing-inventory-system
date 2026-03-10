package com.example.billGenerationSystem.service;

import com.example.billGenerationSystem.dtos.BillDTO;
import com.example.billGenerationSystem.dtos.OrderRequestDTO;
import com.example.billGenerationSystem.dtos.OrderResponseDTO;
import com.example.billGenerationSystem.model.CustomerModel;
import com.example.billGenerationSystem.model.OrderModel;
import com.example.billGenerationSystem.model.ProductModel;
import com.example.billGenerationSystem.repository.CustomerRepo;
import com.example.billGenerationSystem.repository.OrderRepo;
import com.example.billGenerationSystem.repository.ProductRepo;
import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class OrderService {

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    ProductRepo productRepo;



    @Value("${twilio.account.sid}")
    private String ACCOUNT_SID;

    @Value("${twilio.acount.auth.token}")
    private String AUTH_TOKEN ;

    @Value("${app.admin.phoneNumber}")
    private String adminNum;

    @Value("${twilio.phone.number}")
    private String twilioPhoneNum;

    @Value("${twilio.whatsapp.number}")
    private String twilioWhatsAppNum;




    public OrderResponseDTO placeOredr(OrderRequestDTO dto) {
        CustomerModel customer = customerRepo.findById(dto.getcId()).orElse(null);
        if(customer == null){
            return new OrderResponseDTO(null, HttpStatus.OK,"No Customer Found");
        }

        ProductModel  productModel = productRepo.findById(dto.getpId()).orElse(null);
        if(productModel == null){
            return new OrderResponseDTO(null, HttpStatus.OK,"No Product Found");
        }

        if(productModel.getStock() < dto.getQuantity()){
            return new OrderResponseDTO(null,HttpStatus.OK,"Quantity Not Available!!! Available Stock : "+productModel.getStock());
        }

        if(!makePayment()){
            return new OrderResponseDTO(null,HttpStatus.OK,"Payment Failed⚠️");
        }

        productRepo.updateStock(dto.getQuantity(),productModel.getId());

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        if( productRepo.getCurrentStock(productModel.getId()) <= productModel.getThreshHold()){
            Message threshHoldMessage = Message
                    .creator(

                            new PhoneNumber(adminNum),
                            new PhoneNumber(twilioPhoneNum),

                            "Remain Stock of Product : "+productModel.getName()+" is Less than ThreshHold"
                    )
                    .create();
        }

        double totalPrice = dto.getQuantity() * productModel.getPrice();
        double gstAmount = totalPrice*productModel.getGst()/100;

        orderRepo.save(new OrderModel(customer,productModel,dto.getQuantity(),totalPrice+gstAmount));



        Message message = Message
                .creator(

                        new PhoneNumber("+91"+customer.getMobile()),
                        new PhoneNumber(twilioPhoneNum),

                        "Order Confirmed Successfully"
                )
                .create();


        Message message1 = Message
                .creator(

                        new PhoneNumber("whatsapp:+91"+customer.getMobile()),
                        new PhoneNumber(twilioWhatsAppNum),

                        "Order Confirmed Successfully📦 Name : "+productModel.getName()+", Price : "+productModel.getPrice()+", Quantity :"+dto.getQuantity()+", Total Amount(With GST) : "+(totalPrice+gstAmount)
                )
                .create();

        return new OrderResponseDTO(new BillDTO(customer.getName(), productModel.getName(), productModel.getPrice(),dto.getQuantity(), gstAmount,totalPrice+gstAmount ),HttpStatus.OK,"Order Placed Successfully");



    }
    private boolean makePayment(){
        return ThreadLocalRandom.current().nextInt(5) != 0;
    }
}
