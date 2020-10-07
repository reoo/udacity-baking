package com.udacity.baking.baker.detail;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.udacity.baking.baker.databinding.FragmentStepDetailBinding;
import com.udacity.baking.baker.model.Step;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

public class StepDetailFragment extends Fragment implements RecipeDetailAdapter.RecipeClickListener, Player.EventListener {
    public static final String TAG = StepDetailFragment.class.getSimpleName();

    private static final String EXTRA_STEP = "EXTRA_STEP";

    @NonNull
    private FragmentStepDetailBinding binding;
    @Nullable
    private Step step;
    @Nullable
    private RecipeDetailListener listener;
    @Nullable
    private SimpleExoPlayer mExoPlayer;

    public interface RecipeDetailListener {
        void onStepClick(@NonNull Step step);
    }

    public static StepDetailFragment newInstance(@Nullable Step step) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_STEP, step);
        StepDetailFragment fragment = new StepDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        restoreInstanceState(savedInstanceState);
        loadArguments(getArguments());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentStepDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (step != null) {
            getActivity().setTitle(step.shortDescription);
            binding.stepDetailDescription.setText(step.description);

            String url = null;
            if(step.videoURL != null && !step.videoURL.isEmpty()) {
                url = step.videoURL;
            } else if(step.thumbnailURL != null && !step.thumbnailURL.isEmpty()) {
                url = step.thumbnailURL;
            }
            if(url != null) {
                initializePlayer(Uri.parse(url));
            } else {
                binding.stepDetailVideoPlayerView.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof RecipeDetailListener) {
            listener = (RecipeDetailListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(EXTRA_STEP, step);
    }

    @Override
    public void onStepClick(@NonNull Step step) {
        if (listener != null) {
            listener.onStepClick(step);
        }
    }

    private void restoreInstanceState(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            step = savedInstanceState.getParcelable(EXTRA_STEP);
        }
    }

    private void loadArguments(@Nullable Bundle arguments) {
        if (arguments != null) {
            step = arguments.getParcelable(EXTRA_STEP);
        }
    }


    /**
     * Initialize ExoPlayer.
     * @param mediaUri The URI of the sample to play.
     */
    private void initializePlayer(Uri mediaUri) {
        if (mExoPlayer == null) {
            FragmentActivity activity = getActivity();
            if(activity != null) {
                // Create an instance of the ExoPlayer.
                TrackSelector trackSelector = new DefaultTrackSelector();
                LoadControl loadControl = new DefaultLoadControl();
                mExoPlayer = ExoPlayerFactory.newSimpleInstance(activity, trackSelector, loadControl);
                binding.stepDetailVideoPlayerView.setPlayer(mExoPlayer);

                // Set the ExoPlayer.EventListener to this activity.
                mExoPlayer.addListener(this);

                // Prepare the MediaSource.
                String userAgent = Util.getUserAgent(activity, "VideoScreen");
                MediaSource mediaSource = new ExtractorMediaSource(mediaUri,
                        new DefaultDataSourceFactory(activity, userAgent),
                        new DefaultExtractorsFactory(), null, null
                );
                mExoPlayer.prepare(mediaSource);
                mExoPlayer.setPlayWhenReady(true);
            }
        }
    }

    /**
     * Release ExoPlayer.
     */
    private void releasePlayer() {
        if (mExoPlayer != null) {
            mExoPlayer.stop();
            mExoPlayer.release();
            mExoPlayer = null;
        }
    }

}