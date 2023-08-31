package com.simplepay.membership.adapter.out.persistence;

import com.simplepay.membership.application.port.out.FindMembershipPort;
import com.simplepay.membership.application.port.out.RegisterMembershipPort;
import com.simplepay.membership.domain.Membership;
import common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class MembershipPersistenceAdapter implements RegisterMembershipPort, FindMembershipPort {

    private final SpringDataMembershipRepository membershipRepository;

    @Override
    public MembershipJpaEntity createMembership(
            Membership.MembershipName membershipName,
            Membership.MembershipEmail membershipEmail,
            Membership.MembershipAddress membershipAddress,
            Membership.MembershipIsValid membershipIsValid,
            Membership.MembershipIsCorp membershipIsCorp
    ) {
        return membershipRepository.save(
                new MembershipJpaEntity(
                        membershipName.getName(),
                        membershipEmail.getEmail(),
                        membershipAddress.getAddress(),
                        membershipIsValid.getIsValid(),
                        membershipIsCorp.getIsCorp()
                )
        );
    }

    @Override
    public MembershipJpaEntity findMembership(Membership.MembershipId membershipId) {
        membershipRepository.findById(Long.valueOf(membershipId.getMembershipId()+""));
        return null;
    }
}
