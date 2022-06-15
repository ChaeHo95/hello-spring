package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;


/*
* repository는 단순히 기술 의존적으로 네이밍 하지만 service쪽은 비지니스 의존적으로 네이밍*/
public class MemberService {
    // Ctrl + Shift + T 를 누를 시 자동으로 테스트를 만들어줌
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
     * 회원 가입
     */
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원 X

        // 로직이 나올 때는 메소드로 뽑는게 좋기 때문에 메소드로 전환
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        // 중복 회원 검증

        // Optional<> 바로 반환하는 것은 권장하지 않아
        // 반환 값이 Optional<> 이기때문에 이런 식으로 코드 작성 사용
        memberRepository.findByName(member.getName()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });

        /*Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> { //ifPresent() 메소드는 만약 값이 있으면 로직이 동작함
            // 과거에는 if를 이용하여 하였지만 현재는 Optional<> 클래스의 ifPresent() 메소드를  많이 사용함.
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });*/
    }

    /*
    * 전체 회원 조회
    * */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    /*
    * 특정 회원 조회
    * */
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
