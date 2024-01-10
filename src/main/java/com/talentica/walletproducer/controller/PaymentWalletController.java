package com.talentica.walletproducer.controller;

import com.talentica.walletproducer.dto.*;
import com.talentica.walletproducer.service.CheckBalanceServiceImpl;
import com.talentica.walletproducer.service.TransactionHistoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/wallet")
public class PaymentWalletController {

    @Autowired
    @Qualifier("checkBalanceService")
    private CheckBalanceServiceImpl checkBalanceService;

    @Autowired
    @Qualifier("TxnHstService")
    private TransactionHistoryServiceImpl transactionHistoryServiceImpl;

    @GetMapping("/balance")
    public ResponseEntity<UserWalletDto> getBalance(@RequestBody UsersDto usersDto) {
        UserWalletDto userWalletDto = new UserWalletDto();
        userWalletDto.setUserId(usersDto.getUserId());
        userWalletDto.setBalance(checkBalanceService.getBalance(usersDto));
        return new ResponseEntity<>(userWalletDto, HttpStatus.OK);
    }

    @GetMapping("/history")
    public ResponseEntity<List<UserTransactionHistoryDto>> getTransactionHistory(@RequestBody UsersDto usersDto) {
        List<UserTransactionHistoryDto> userTxnHstDtos =
                transactionHistoryServiceImpl.
                        getTransactionHistory(usersDto);
        return new ResponseEntity<>(userTxnHstDtos, HttpStatus.OK);
    }

    @PutMapping("/transfer")
    public ResponseEntity transferFunds(@RequestBody TransferRequestDto requestDto) {
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
