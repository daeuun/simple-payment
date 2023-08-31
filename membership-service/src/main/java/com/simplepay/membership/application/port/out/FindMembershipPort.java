package com.simplepay.membership.application.port.out;

import com.simplepay.membership.adapter.out.persistence.MembershipJpaEntity;
import com.simplepay.membership.domain.Membership;

public interface FindMembershipPort {

    MembershipJpaEntity findMembership(
            Membership.MembershipId membershipId
    );
}
