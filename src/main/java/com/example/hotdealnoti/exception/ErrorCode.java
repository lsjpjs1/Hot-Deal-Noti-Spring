package com.example.hotdealnoti.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    PASSWORD_WRONG(HttpStatus.UNAUTHORIZED, "Password is wrong"),
    EMAIL_NOT_VERIFIED(HttpStatus.UNAUTHORIZED, "Email is not verified"),

    NOT_CLUB_MEMBER(HttpStatus.FORBIDDEN, "This post can read just club member"),
    NOT_SCHOOL_MEMBER(HttpStatus.FORBIDDEN, "This post can read just school member"),
    NO_PERMISSION(HttpStatus.FORBIDDEN, "권한이 없습니다."),

    USER_INFO_NOT_FOUND(HttpStatus.NOT_FOUND, "User information cannot found from token. Please sign in again."),
    ACCOUNT_NOT_FOUND(HttpStatus.NOT_FOUND, "유저 정보를 찾을 수 없습니다."),
    USER_ID_NOT_FOUND(HttpStatus.NOT_FOUND,"UserId cannot found."),
    USER_EMAIL_NOT_FOUND(HttpStatus.NOT_FOUND,"UserEmail cannot found."),
    USER_PROFILE_NOT_FOUND(HttpStatus.NOT_FOUND,"UserProfile cannot found."),
    PROFILE_IMAGE_ID_NOT_FOUND(HttpStatus.NOT_FOUND,"ProfileImageId cannot found."),
    OS_TYPE_NOT_FOUND(HttpStatus.NOT_FOUND,"OS type cannot found."),
    VERIFICATION_CODE_NOT_FOUND(HttpStatus.NOT_FOUND,"VerificationCode cannot found."),
    SCHOOL_ID_NOT_FOUND(HttpStatus.NOT_FOUND,"SchoolId cannot found."),
    POST_NOT_FOUND(HttpStatus.NOT_FOUND,"Post cannot found."),
    POST_DELETED(HttpStatus.NOT_FOUND,"Post was deleted"),
    POST_WRITER_BLOCKED(HttpStatus.NOT_FOUND,"Writer of post was blocked"),
    VERIFICATION_CODE_WRONG(HttpStatus.NOT_FOUND,"인증번호가 잘못되었습니다."),
    VERIFICATION_CODE_NOT_EXIST(HttpStatus.NOT_FOUND,"인증번호 정보가 없습니다. 인증 메일을 먼저 발송해주세요."),
    JOB_NOT_FOUND(HttpStatus.NOT_FOUND,"Job cannot found."),
    JOB_FAVORITE_NOT_FOUND(HttpStatus.NOT_FOUND,"JobFavorite cannot found."),
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND,"Category cannot found."),
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND,"Comment cannot found."),
    POST_MESSAGE_NOT_FOUND(HttpStatus.NOT_FOUND,"Post message cannot found."),
    PARTY_NOT_FOUND(HttpStatus.NOT_FOUND,"Party cannot found."),
    PARTY_MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND,"PartyMember cannot found."),
    PARTY_APPLY_GROUP_NOT_FOUND(HttpStatus.NOT_FOUND,"PartyApplyGroup cannot found."),
    HOT_DEAL_ID_NOT_FOUND(HttpStatus.NOT_FOUND,"HotDeal cannot found."),
    PRODUCT_ID_NOT_FOUND(HttpStatus.NOT_FOUND,"Product cannot found."),
    PRODUCT_FAMILY_ID_NOT_FOUND(HttpStatus.NOT_FOUND,"Product family cannot found."),
    PRODUCT_PURPOSE_DETAIL_ID_NOT_FOUND(HttpStatus.NOT_FOUND,"Product purpose detail cannot found."),
    KEYWORD_NOTIFICATION_ID_NOT_FOUND(HttpStatus.NOT_FOUND,"존재하지 않는 키워드입니다."),
    FAVORITE_HOT_DEAL_NOT_FOUND(HttpStatus.NOT_FOUND,"존재하지 않는 즐겨찾기 입니다."),

    BAD_REQUEST_CONTENT(HttpStatus.NOT_ACCEPTABLE,"Please check params or body"),
    WRONG_EMAIL_FORM(HttpStatus.NOT_ACCEPTABLE,"Email form is wrong"),
    ALREADY_PROCESSED(HttpStatus.NOT_ACCEPTABLE,"This work has already been processed"),
    IMAGE_UPLOAD_NOT_COMPLETE(HttpStatus.NOT_ACCEPTABLE,"Image upload not complete yet"),
    NOT_CLUB_CATEGORY(HttpStatus.NOT_ACCEPTABLE,"CategoryId does not club"),
    PARTY_MEMBER_TOO_SMALL(HttpStatus.NOT_ACCEPTABLE,"The number of people should be more than 5."),
    PARTY_CANCELED(HttpStatus.NOT_ACCEPTABLE,"This party was canceled"),
    NOTIFICATION_KEYWORD_COUNT_LIMIT(HttpStatus.NOT_ACCEPTABLE,"키워드는 최대 5개까지만 등록할 수 있습니다."),
    NOTIFICATION_KEYWORD_LENGTH_TOO_SHORT(HttpStatus.NOT_ACCEPTABLE,"키워드는 두 글자 이상 입력해주세요."),


    DUPLICATE_RESOURCE(HttpStatus.CONFLICT, "Data already exists."),
    EMAIL_ALREADY_EXIST(HttpStatus.CONFLICT, "Email already exists."),
    JOB_FAVORITE_ALREADY_EXIST(HttpStatus.CONFLICT, "Already favorite this job"),
    ALREADY_CLUB_MEMBER(HttpStatus.CONFLICT, "You are already club member"),
    PARTY_ALREADY_EXIST(HttpStatus.CONFLICT, "Party already exists on that date."),

    SEND_EMAIL_FAIL(HttpStatus.INTERNAL_SERVER_ERROR,"이메일 전송 실패"),
    PAYMENT_FAIL(HttpStatus.INTERNAL_SERVER_ERROR,"Fail to payment"),
    LOGIN_FAIL(HttpStatus.INTERNAL_SERVER_ERROR,"로그인 실패")
        ;
    private final HttpStatus httpStatus;
    private final String message;
}
