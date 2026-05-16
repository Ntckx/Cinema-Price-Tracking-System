package com.ntck.cinematickettrackingsystem.Pricing.dto.request.OnTop;

import com.ntck.cinematickettrackingsystem.models.MemberTier.MemberTier;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OnTopRequest {
    @NotNull(message = "On Top type is required")
    private OnTopType onTopType;

    @NotNull(message = "Member type is required")
    private MemberTier memberTier;

    @Positive(message = "Point should be more than 0")
    private int points;
}
