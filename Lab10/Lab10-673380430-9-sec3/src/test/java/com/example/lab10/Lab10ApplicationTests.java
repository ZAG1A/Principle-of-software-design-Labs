package com.example.lab10;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.lab10.model.Product;
import com.example.lab10.repository.ProductRepository;

import reactor.test.StepVerifier;

/**
 * Lab10ApplicationTests — ทดสอบ Reactive code
 *
 * ✅ test findById() ทำเสร็จแล้วเป็นตัวอย่าง
 * ❌ TODO: เพิ่ม test สำหรับ method ที่นักศึกษาทำเอง
 *
 * StepVerifier — วิธีทดสอบ Mono/Flux:
 *   StepVerifier.create(mono/flux)
 *     .expectNext(value)     ← คาดหวังค่าที่ได้
 *     .expectNextCount(n)    ← คาดหวังจำนวน element
 *     .verifyComplete()      ← ยืนยัน onComplete
 *     .verifyError()         ← ยืนยัน onError
 */
@SpringBootTest
class Lab10ApplicationTests {

    @Autowired
    private ProductRepository repository;

    // ══════════════════════════════════════════════════════
    // ✅ ตัวอย่าง test — ศึกษาแล้วเพิ่ม test เอง
    // ══════════════════════════════════════════════════════

    @Test
    void contextLoads() {
        // Spring Application Context โหลดสำเร็จ
    }

    @Test
    void testFindById_found() {
        // ✅ ตัวอย่าง: ทดสอบ findById ที่พบข้อมูล
        StepVerifier.create(repository.findById("1"))
                .expectNextMatches(p -> p.getName().contains("iPhone"))
                .verifyComplete();
    }

    @Test
    void testFindById_notFound() {
        // ✅ ตัวอย่าง: ทดสอบ findById ที่ไม่พบข้อมูล
        StepVerifier.create(repository.findById("999"))
                .verifyComplete(); // Mono.empty() → onComplete ทันที
    }

    // ══════════════════════════════════════════════════════
    // ❌ TODO: เพิ่ม test ด้านล่างนี้
    // ══════════════════════════════════════════════════════

    @Test
    void testFindAll() {
        // ทดสอบว่า findAll() มีข้อมูลส่งกลับมาอย่างน้อย 3 รายการ
        StepVerifier.create(repository.findAll())
                .expectNextCount(3)
                .thenConsumeWhile(p -> true) // เคลียร์ element ที่เหลืออยู่ (ถ้ามี)
                .verifyComplete();
    }

    @Test
    void testSave() {
        // สร้าง Product ใหม่เพื่อทดสอบการบันทึก
        Product newProduct = new Product("4", "iPad Air", "Electronics", "Apple", 15, 23900.0, "NONE");

        StepVerifier.create(repository.save(newProduct))
                .expectNextMatches(p -> p.getId().equals("4") && p.getName().equals("iPad Air"))
                .verifyComplete();
    }

    @Test
    void testFindByCategory() {
        // กรองเฉพาะหมวด Electronics และรับข้อมูลทั้งหมดที่ตรงตามเงื่อนไข
        StepVerifier.create(repository.findByCategory("Electronics"))
                .thenConsumeWhile(p -> p.getCategory().equalsIgnoreCase("Electronics"))
                .verifyComplete();
    }
}