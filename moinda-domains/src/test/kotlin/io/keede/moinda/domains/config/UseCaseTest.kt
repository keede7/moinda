package io.keede.moinda.domains.config

import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.DisplayNameGeneration
import org.junit.jupiter.api.DisplayNameGenerator
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores::class)
annotation class UseCaseTest
