/**
 * Payment Controller
 *
 * @author Peiyuan
 * 2019-03-15
 */

package com.minipgm.transaction.payment;

import com.minipgm.util.sessionCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/detail")
    public List<Payment> getPaymentDetail(@RequestBody Map<String, Object> param, HttpSession session) {
        try {
            if (sessionCheck.isOnline(session, "ADMIN")) {
                return paymentService.getPaymentDetail(Integer.parseInt(param.get("resId").toString()));
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
