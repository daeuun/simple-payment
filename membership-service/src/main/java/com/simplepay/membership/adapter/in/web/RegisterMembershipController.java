package com.simplepay.membership.adapter.in.web;

import com.simplepay.membership.application.port.in.RegisterMembershipCommand;
import com.simplepay.membership.application.port.in.RegisterMembershipUseCase;
import com.simplepay.membership.domain.Membership;
import common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RegisterMembershipController {

    private final RegisterMembershipUseCase registerMembershipUseCase;

    @PostMapping
    Membership registerMembership(@RequestBody RegisterMembershipRequest request) {
        // request -> command 로 변경
        // Usecase (requset x, command o)
        RegisterMembershipCommand command = RegisterMembershipCommand.builder()
                .name(request.getName())
                .address(request.getAddress())
                .email(request.getEmail())
                .isValid(true)
                .isCorp(request.isCorp())
                .build();

        return registerMembershipUseCase.registerMembership(command);
    }
}
