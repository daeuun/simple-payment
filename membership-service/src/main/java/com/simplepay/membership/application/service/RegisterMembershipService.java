package com.simplepay.membership.application.service;

import com.simplepay.membership.adapter.out.persistence.MembershipJpaEntity;
import com.simplepay.membership.adapter.out.persistence.MembershipMapper;
import com.simplepay.membership.application.port.in.RegisterMembershipCommand;
import com.simplepay.membership.application.port.in.RegisterMembershipUseCase;
import com.simplepay.membership.application.port.out.RegisterMembershipPort;
import com.simplepay.membership.domain.Membership;
import common.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional
public class RegisterMembershipService implements RegisterMembershipUseCase {

    private final RegisterMembershipPort registerMembershipPort;
    private final MembershipMapper membershipMapper;

    @Override
    public Membership registerMembership(RegisterMembershipCommand command) {
        // command -> DB
        MembershipJpaEntity membership = registerMembershipPort.createMembership(
                new Membership.MembershipName(command.getName()),
                new Membership.MembershipEmail(command.getEmail()),
                new Membership.MembershipAddress(command.getAddress()),
                new Membership.MembershipIsValid(command.isValid()),
                new Membership.MembershipIsCorp(command.isCorp())
        );
        // entity -> Membership domain
        return membershipMapper.mapToDomainEntity(membership);
    }
}
