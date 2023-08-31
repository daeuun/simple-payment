package com.simplepay.membership.application.port.in;

import com.simplepay.membership.domain.Membership;

public interface RegisterMembershipUseCase {

    Membership registerMembership(RegisterMembershipCommand command);
}
