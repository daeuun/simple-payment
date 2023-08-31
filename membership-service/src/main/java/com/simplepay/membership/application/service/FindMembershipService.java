package com.simplepay.membership.application.service;

import com.simplepay.membership.adapter.out.persistence.MembershipJpaEntity;
import com.simplepay.membership.adapter.out.persistence.MembershipMapper;
import com.simplepay.membership.application.port.in.FindMembershipCommand;
import com.simplepay.membership.application.port.in.FindMembershipUseCase;
import com.simplepay.membership.application.port.out.FindMembershipPort;
import com.simplepay.membership.domain.Membership;
import common.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional
public class FindMembershipService implements FindMembershipUseCase {

    private final FindMembershipPort findMembershipPort;
    private final MembershipMapper membershipMapper;

    @Override
    public Membership findMembership(FindMembershipCommand command) {
        MembershipJpaEntity entity = findMembershipPort.findMembership(new Membership.MembershipId(command.getMembershipId()+""));
        return membershipMapper.mapToDomainEntity(entity);
    }
}
